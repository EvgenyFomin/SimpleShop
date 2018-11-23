package ru.study.simpleshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.study.simpleshop.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
