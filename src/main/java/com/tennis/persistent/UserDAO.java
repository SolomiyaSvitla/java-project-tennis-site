package com.tennis.persistent;

import com.tennis.domain.User;

public interface UserDAO {

	void create(User user);

	User getById(int id);
}