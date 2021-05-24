package io.github.sysker.example20210518;

import io.github.sysker.example20210518.annotation.UseCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * tracker
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-19 7:57
 */
public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method declaredMethod : cl.getDeclaredMethods()) {
            UseCase uc = declaredMethod.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (Integer useCase : useCases) {
            System.out.println("Warning: Missing use case-" + useCase);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
