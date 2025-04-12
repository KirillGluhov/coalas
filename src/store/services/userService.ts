import { createApi } from "@reduxjs/toolkit/query/react";
import { endpoints } from "./endpoints";
import { baseQueryWithAuth } from "./baseQueryWithAuth";
import { PositionType, UserType } from "./types/dataTypes";

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
        getPosition: build.query<PositionType, string>({
            query: (id) => ({
                url: endpoints.user.position.replace(":id", id),
                method: "GET"
            }),
            providesTags: ['UserChanged']
        }),
    })
})