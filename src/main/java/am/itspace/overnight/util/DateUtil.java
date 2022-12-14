package am.itspace.overnight.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
public class DateUtil {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date stringToDate(String dataStr) {
        try {
            return simpleDateFormat.parse(dataStr);
        } catch (ParseException e) {
          log.error("Error parsing date. message:  {}", e.getMessage() );
        }
        return null;
    }

}
