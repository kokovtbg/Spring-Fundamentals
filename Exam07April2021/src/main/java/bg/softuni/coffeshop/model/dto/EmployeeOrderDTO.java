package bg.softuni.coffeshop.model.dto;

public class EmployeeOrderDTO {
    private String username;
    private int ordersCount;

    public EmployeeOrderDTO() {
    }

    public EmployeeOrderDTO(String username, int ordersCount) {
        this.username = username;
        this.ordersCount = ordersCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }
}
