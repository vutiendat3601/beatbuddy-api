package vn.io.vutiendat3601.beatbuddy.api.common.type;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pagination<T> {
  private List<T> items;

  private int page;

  private int size;

  private int numOfItems;

  private long totalItems;

  private int totalPages;

  public <R> Pagination<R> map(Function<T, R> mapper) {
    return new Pagination<>(
        items.stream().map(mapper).toList(), page, size, numOfItems, totalItems, totalPages);
  }

  public void forEach(Consumer<? super T> consumer) {
    items.forEach(consumer::accept);
  }

  public static <R> Pagination<R> of(Page<R> page) {
    return new Pagination<>(
        page.getContent(),
        page.getNumber() + 1,
        page.getSize(),
        page.getNumberOfElements(),
        page.getTotalElements(),
        page.getTotalPages());
  }

  public static Pagination<?> empty() {
    return new Pagination<>(List.of(), 0, 0, 0, 0, 0);
  }
}
