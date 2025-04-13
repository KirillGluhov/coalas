import { createApi } from "@reduxjs/toolkit/query/react";
import { endpoints } from "./endpoints";
import { baseQueryWithAuth } from "./baseQueryWithAuth";
import { EmployeeRegisterForm, PositionType, UserType } from "./types/dataTypes";

export const userAPI = createApi({
    reducerPath: endpoints.user.api,
    baseQuery: baseQueryWithAuth,
    tagTypes: ['UserChanged'],
    endpoints: (build) => ({
        getUser: build.query<UserType, string>({
            query: (id) => ({
                url: endpoints.user.id.replace(":id", id),
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
        getEmployees: build.query<UserType[], void>({
            query: () => ({
                url: endpoints.user.search.employees,
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
        getClients: build.query<UserType[], void>({
            query: () => ({
                url: endpoints.user.search.clients,
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
        blockUser: build.mutation<any, string>({
            query: (id) => ({
                url: endpoints.user.block.replace(":id", id),
                method: "DELETE"
            }),
            invalidatesTags: ['UserChanged']
        }),
        registerEmployee: build.mutation<any, EmployeeRegisterForm>({
            query: (employee) => ({
                url: endpoints.user.create.employee,
                method: 'POST',
                body: employee
            }),
            invalidatesTags: ['UserChanged']
        }),
        getPosition: build.query<PositionType, string>({
            query: (id) => ({
                url: endpoints.user.position.replace(":id", id),
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
        getPositions: build.query<PositionType[], void>({
            query: () => ({
                url: endpoints.user.positions,
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
    })
})