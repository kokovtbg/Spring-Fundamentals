package bg.softuni.coffeshop.service;

import bg.softuni.coffeshop.model.Category;
import bg.softuni.coffeshop.model.Order;
import bg.softuni.coffeshop.model.User;
import bg.softuni.coffeshop.model.dto.OrderAddDTO;
import bg.softuni.coffeshop.model.dto.OrderViewDTO;
import bg.softuni.coffeshop.repository.CategoryRepository;
import bg.softuni.coffeshop.repository.OrderRepository;
import bg.softuni.coffeshop.repository.UserRepository;
import bg.softuni.coffeshop.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CategoryRepository categoryRepository,
                        UserRepository userRepository,
                        CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void addOrder(OrderAddDTO orderAdd) {
        Order order = new Order();
        order.setName(orderAdd.getName());
        order.setPrice(orderAdd.getPrice());
        order.setDescription(orderAdd.getDescription());
        order.setOrderTime(orderAdd.getOrderTime());
        Category category = this.categoryRepository.findByName(orderAdd.getCategory());
        order.setCategory(category);
        User user = this.userRepository.findByUsername(this.currentUser.getUsername()).get();
        order.setEmployee(user);
        this.orderRepository.save(order);
    }

    public List<OrderViewDTO> getAllSorted() {
        List<Order> orders = this.orderRepository.findAllByOrderByPriceDesc();
        return orders.stream()
                .map(o -> new OrderViewDTO(o.getId(), o.getName(), o.getPrice(), o.getCategory().getName()))
                .collect(Collectors.toList());
    }

    public int getAllTimeNeeded() {
        List<Order> allOrders = this.orderRepository.findAll();
        return allOrders.stream()
                .mapToInt(o -> o.getCategory().getNeededTime())
                .sum();
    }

    @Transactional
    public void delete(long id) {
        this.orderRepository.deleteById(id);
    }
}
