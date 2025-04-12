import { createApi } from "@reduxjs/toolkit/query/react";
import { endpoints } from "./endpoints";
import { baseQueryWithAuth } from "./baseQueryWithAuth";
import { AuthType, ProfileType, RefreshTokenType, TokenType } from "./types/dataTypes";
import { OptionalLoginForm } from "../../pages/containers/simple-container/login/form/Types";

export const authentificationAPI = createApi({
    reducerPath: endpoints.authentification.api,
    baseQuery: baseQueryWithAuth,
    tagTypes: ['Entered'],
    endpoints: (build) => ({
        loginIntoAccount: build.mutation<AuthType, OptionalLoginForm>({
            query: (loginData) => ({
                url: endpoints.authentification.login,
                method: 'POST',
                body: loginData
            }),
            invalidatesTags: ['Entered']
        }),
        getProfile: build.query<ProfileType, void>({
            query: () => ({
                url: endpoints.authentification.profile,
                method: "GET"
            }),
            providesTags: ['Entered']
        }),
        getRefreshToken: build.query<TokenType, RefreshTokenType>({
            query: (tokens) => ({
                url: endpoints.authentification.refresh,
                method: 'POST',
                body: tokens
            }),
            providesTags: ['Entered']
        })
    })
})