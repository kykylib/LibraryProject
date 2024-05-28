package com.library.LibraryProject.repository;

import com.library.LibraryProject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

