package basti.p.backend.playlist;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import basti.p.model.entity.User;
import basti.p.model.viewmodel.UserViewModel;

@Service
public class UserService {

	@Autowired
	private IUserRepository repo;

	public List<UserViewModel> getUsers() {
		return Lists.newArrayList(repo.findAll()).stream().map(this::toViewModel).collect(toList());
	}

	public void addUser(UserViewModel user) {
		repo.save(toEntity(user));
	}

	private UserViewModel toViewModel(User user) {
		return new UserViewModel(user.getName(), user.getPlayerId());
	}

	private User toEntity(UserViewModel user) {
		return new User(user.getPlayerId(), user.getName());
	}
}
