package io.star.service;

import java.util.List;

import io.star.models.Category;

public interface CategoryService {

	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category get(int id);

	Category get(String name);

	List<Category> getAll();

	List<Category> search(String keyword);
}
