package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category {
    @Getter
    @EqualsAndHashCode.Include
    @NonNull
    private Integer id;
    @Getter
    @Setter
    private String name;
}