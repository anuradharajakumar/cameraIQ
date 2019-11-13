package test.cameriq.interview.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MainService<T> {
    Page<T> getallOrganizations(Pageable pageable);

    T add(T o);
    T getById(int id);
    T update(T o, int id);
    T deleteById(int id);

}
