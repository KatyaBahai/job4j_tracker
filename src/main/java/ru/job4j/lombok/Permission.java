package ru.job4j.lombok;

import lombok.*;

import java.util.List;

@Builder(builderMethodName = "of")
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Permission {
    @NonNull
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    @Singular("setRules")
    private List<String> rules;
}