package pl.sdacademy.java.jdbc.homework2;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import pl.sdacademy.java.jdbc.model.Actor;

import java.util.List;

public interface ActorMapper {

    @Select({
        "select distinct a.first_name, a.last_name",
        "from actor a",
        "    join film_actor fa on a.actor_id = fa.actor_id",
        "    join film f on fa.film_id = f.film_id",
        "where",
        "    upper(first_name) like upper(#{query}) or",
        "    upper(last_name) like upper(#{query}) or",
        "    upper(title) like upper(#{query})",
        "order by last_name, first_name;"
    })
    @Results(value = {
        @Result(property = "firstName", column = "first_name"),
        @Result(property= "lastName", column = "last_name")
    })
    List<Actor> getActors(String query);
}
