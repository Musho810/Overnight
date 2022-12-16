package am.itspace.overnight.exception;

public class ProductNotFoundException extends IllegalArgumentException{


    public ProductNotFoundException(int id) {
        super(String.format("Product with Id %d not found", id));
    }
}

