package basti.p.backend.playlist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import basti.p.model.entity.Playlist;

@Repository
public interface IPlayListRepository extends CrudRepository<Playlist, Integer> {

}
