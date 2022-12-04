package am.itspace.overnight.service;

import am.itspace.overnight.entity.RoleUser;
import am.itspace.overnight.entity.StatusSeller;
import am.itspace.overnight.entity.User;
import am.itspace.overnight.entity.UserBook;
import am.itspace.overnight.repository.UserBookRepository;
import am.itspace.overnight.repository.UserRepository;
import am.itspace.overnight.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import static am.itspace.overnight.util.DateUtil.simpleDateFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final UserRepository userRepository;
    private final UserBookRepository userBookRepository;
    private final MailService mailService;
    @Value("${overnight.images.folder}")
    private String folderPath;


    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            byId.get().setName(user.getName());
            byId.get().setSurname(user.getSurname());
            byId.get().setEmail(user.getEmail());
            byId.get().setPhoneNumber(user.getPhoneNumber());
            byId.get().setStatus(user.getStatus());
            userRepository.save(byId.get());
        }
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    public Page<User> findUsersByUserRole(RoleUser seller, Pageable pageable, StatusSeller status) {
        if (status == null) {
            return userRepository.findUserByRole(seller, pageable);
        }
        return userRepository.findUserByRoleAndStatus(seller, status, pageable);
    }

    public void edit(int id, StatusSeller status) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setStatus(status);
            userRepository.save(user);

            if (user.getStatus().equals(StatusSeller.ACTIVE)) {
                mailService.sendEmail(user.getEmail(), "WELCOME", "Hi " + user.getName() + " \n" +
                        " Your profile is activated!!!");
            } else {
                mailService.sendEmail(user.getEmail(), "WARNING", "Hi " + user.getName() + " \n" +
                        " Your profile is blocked!!!");
            }
        }
    }

    public Page<UserBook> findUserBookAll(Pageable pageable, String startDate, String endDate, String keyword) {

        Date from = null;
        Date to = null;
        try {
            from = StringUtils.isEmpty(endDate) ? null : simpleDateFormat.parse(startDate);
            to = StringUtils.isEmpty(startDate) ? null : simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            log.error("Error parsing date. message: {}", e.getMessage());
        }
        if (from != null && to != null) {
            return userBookRepository.findByStartDateOrEndDateBetween(pageable, from, to);
        } else if (keyword != null) {
            return userBookRepository.findByProductNameContaining(pageable, keyword);

        } else {
            return userBookRepository.findAll(pageable);
        }
    }

    public void image(CurrentUser currentUser, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            currentUser.getUser().setPicUrl(fileName);
            userRepository.save(currentUser.getUser());
        }

    }
}

