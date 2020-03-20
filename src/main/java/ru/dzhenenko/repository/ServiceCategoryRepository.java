package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.Category;

public interface ServiceCategoryRepository extends JpaRepository<Category, Long> {
}
