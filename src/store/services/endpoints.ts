export const endpoints = {
    baseUrl: `http://81.31.247.55:6060`,
    authentification: {
        api: "authApi",
        login: "/login",
        profile: "/users/my",
        refresh: "/token"
    },
    tariff: {
        api: "tariffApi",
        search: {
            all: "/tariffs"
        },
        create: "/tariffs",
        delete: "/tariffs/:id"
    },
    user: {
        api: "userApi",
        id: "/users/:id",
        position: "/positions/:id"
    }
}