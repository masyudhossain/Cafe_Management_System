package com.restaurant.services.auth.customer;

import com.restaurant.dtos.CategoryDto;
import com.restaurant.dtos.ProductDto;
import com.restaurant.dtos.ReservationDto;
import com.restaurant.entities.Category;
import com.restaurant.entities.Product;
import com.restaurant.entities.Reservation;
import com.restaurant.entities.User;
import com.restaurant.enums.ReservationStatus;
import com.restaurant.repositories.CategoryRepository;
import com.restaurant.repositories.ProductRepository;
import com.restaurant.repositories.ReservationRepository;
import com.restaurant.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getCustomerId());
        if (optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setTableType(reservationDto.getTableType());
            reservation.setDateTime(reservationDto.getDateTime());
            reservation.setDescription(reservationDto.getDescription());
            reservation.setUser(optionalUser.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            Reservation postedReservation = reservationRepository.save(reservation);
            return mapToReservationDto(postedReservation);
        } else {
            // Log and handle user not found scenario
            System.err.println("User not found with ID: " + reservationDto.getCustomerId());
        }
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByUser(Long customerId) {
        return reservationRepository.findAllByUserId(customerId).stream().map(Reservation:: getReservationDto).collect(Collectors.toList());
    }

    private ReservationDto mapToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setTableType(reservation.getTableType());
        reservationDto.setDescription(reservation.getDescription());
        reservationDto.setDateTime(reservation.getDateTime());
        reservationDto.setReservationStatus(reservation.getReservationStatus());
        reservationDto.setCustomerId(reservation.getUser().getId());
        return reservationDto;
    }
}



