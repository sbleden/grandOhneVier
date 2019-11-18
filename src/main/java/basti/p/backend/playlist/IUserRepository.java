package basti.p.backend.playlist;

import org.springframework.data.repository.CrudRepository;

import basti.p.model.entity.User;

public interface IUserRepository extends CrudRepository<User, Integer> {

}
