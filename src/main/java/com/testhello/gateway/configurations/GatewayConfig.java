package com.testhello.gateway.configurations;

import com.testhello.gateway.configurations.filters.JWTAuthorizationFilter;
import com.testhello.gateway.configurations.providers.DomainProvider;
import com.testhello.gateway.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig
{
    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    DomainProvider domainProvider;

    @Bean
    public RouteLocator userAuthRouteLocator(RouteLocatorBuilder builder)
    {
        String url = domainProvider.getUserDomain();
        String partOfId = "user-service-auth-";
        String partOfUrl = "/auth";
        GatewayFilter employeeFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.EMPLOYEE);});
        GatewayFilter withoutFilterRole = jwtAuthorizationFilter.apply(config -> {});

        return builder
                .routes()
                .route(partOfId + "login", r -> r
                        .path(partOfUrl + "/login")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(url))
                .route(partOfId + "logout", r -> r
                        .path(partOfUrl + "/logout")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(withoutFilterRole))
                        .uri(url))
                .route(partOfId + "token", r -> r
                        .path(partOfUrl + "/token")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(withoutFilterRole))
                        .uri(url))
                .route(partOfId + "password", r -> r
                        .path(partOfUrl + "/password")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(withoutFilterRole))
                        .uri(url))
                .route(partOfId + "register-employee", r -> r
                        .path(partOfUrl + "/register/employee")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "register-client", r -> r
                        .path(partOfUrl + "/register/client")
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .build();
        // В пароле есть лишь проверка на действительность токена (что он не истёк и нормально расшифровывается)
    }

    @Bean
    public RouteLocator userUsersRouteLocator(RouteLocatorBuilder builder)
    {
        String url = domainProvider.getUserDomain();
        String partOfId = "user-service-users-";
        String partOfUrl = "/users";

        GatewayFilter clientFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.CLIENT);});
        GatewayFilter employeeFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.EMPLOYEE);});
        GatewayFilter withoutFilterRole = jwtAuthorizationFilter.apply(config -> {});
        GatewayFilter allFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.EMPLOYEE).addRole(Role.CLIENT);});

        return builder
                .routes()
                .route(partOfId + "clients", r -> r
                        .path(partOfUrl + "/clients")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "clients-id", r -> r
                        .path(partOfUrl + "/clients/{userId}")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "employees", r -> r
                        .path(partOfUrl + "/employees")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "employees-id", r -> r
                        .path(partOfUrl + "/employees/{userId}")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "positions", r -> r
                        .path(partOfUrl + "/positions")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "positions-id", r -> r
                        .path(partOfUrl + "/positions/{positionId}")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(allFilterRole))
                        .uri(url))
                .route(partOfId + "id-block", r -> r
                        .path(partOfUrl + "/{userId}/block")
                        .and()
                        .method(HttpMethod.DELETE)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "id-passport-get", r -> r
                        .path(partOfUrl + "/{userId}/passport")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "id-passport-put", r -> r
                        .path(partOfUrl + "/{userId}/passport")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "profile-get", r -> r
                        .path(partOfUrl + "/profile")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(allFilterRole))
                        .uri(url))
                .route(partOfId + "profile-put", r -> r
                        .path(partOfUrl + "/profile")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(allFilterRole))
                        .uri(url))
                .route(partOfId + "profile-passport-put", r -> r
                        .path(partOfUrl + "/profile/passport")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "profile-passport-get", r -> r
                        .path(partOfUrl + "/profile/passport")
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .build();
    }

    @Bean
    public RouteLocator loanTariffsRouteLocator(RouteLocatorBuilder builder)
    {
        String url = domainProvider.getLoanDomain();
        String partOfId = "loan-service-tariffs-";
        String partOfUrl = "/tariffs";

        GatewayFilter employeeFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.EMPLOYEE);});
        GatewayFilter allFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.EMPLOYEE).addRole(Role.CLIENT);});

        return builder
                .routes()
                .route(partOfId + "all", r -> r
                        .path(partOfUrl)
                        .and()
                        .method(HttpMethod.GET)
                        .filters(f -> f
                                .filter(allFilterRole))
                        .uri(url))
                .route(partOfId + "id", r -> r
                        .path(partOfUrl + "/{tariffId}")
                        .and()
                        .method(HttpMethod.DELETE)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .route(partOfId + "post", r -> r
                        .path(partOfUrl)
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(employeeFilterRole))
                        .uri(url))
                .build();

        // Нет проверки на существование кредита для удаления тарифа
    }

    @Bean
    public RouteLocator coreAccountsRouteLocator(RouteLocatorBuilder builder)
    {
        String url = domainProvider.getCoreDomain();
        String partOfId = "core-accounts-";
        String partOfUrl = "/accounts";

        GatewayFilter clientFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.CLIENT);});

        return builder
                .routes()
                .route(partOfId + "id-withdraw", r -> r
                        .path(partOfUrl + "/{accountId}/withdraw")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "id-replenish", r -> r
                        .path(partOfUrl + "/{accountId}/replenish")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "post", r -> r
                        .path(partOfUrl)
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "id-delete", r -> r
                        .path(partOfUrl + "/{accountId}")
                        .and()
                        .method(HttpMethod.DELETE)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .build();
        // Нет проверки на то, что счёт принадлежит владельцу
    }

    @Bean
    public RouteLocator loanLoansRouteLocator(RouteLocatorBuilder builder)
    {
        String url = domainProvider.getLoanDomain();
        String partOfId = "loan-service-loans-";
        String partOfUrl = "/loans";

        GatewayFilter clientFilterRole = jwtAuthorizationFilter.apply(config -> {config.addRole(Role.CLIENT);});

        return builder
                .routes()
                .route(partOfId + "id-account", r -> r
                        .path(partOfUrl + "/{loanId}/accounts/{accountId}/autodebt")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "id-account-delete", r -> r
                        .path(partOfUrl + "/{loanId}/accounts/{accountId}/autodebt")
                        .and()
                        .method(HttpMethod.DELETE)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "post", r -> r
                        .path(partOfUrl)
                        .and()
                        .method(HttpMethod.POST)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .route(partOfId + "id-replenish", r -> r
                        .path(partOfUrl + "/{loanId}/replenish")
                        .and()
                        .method(HttpMethod.PUT)
                        .filters(f -> f
                                .filter(clientFilterRole))
                        .uri(url))
                .build();

        // Нет проверки на существование кредита для удаления тарифа
    }
}

//Переименовать, сделать для всех эндпоинтов, добавить фильтрацию по токену, добавить соединение с фронтом через вебсокеты
