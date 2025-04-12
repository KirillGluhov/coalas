import { fetchBaseQuery } from "@reduxjs/toolkit/query";
import { endpoints } from "./endpoints";
import { RootState } from "../storeTypes";

export const baseQueryWithAuth = fetchBaseQuery({
    baseUrl: endpoints.baseUrl,
    prepareHeaders: async (headers, {getState}) => {
        const state = await getState() as RootState;

        const token = state.userProfileView.authData?.accessToken;

        if (token)
        {
            headers.set('Authorization', `Bearer ${token}`)
        }

        return headers;
    }
})

// Добавить хедеры