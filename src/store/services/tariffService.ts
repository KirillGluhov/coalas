import { createApi } from "@reduxjs/toolkit/query/react";
import { baseQueryWithAuth } from "./baseQueryWithAuth";
import { endpoints } from "./endpoints";
import { TariffFormType, TariffType } from "./types/dataTypes";

export const tariffAPI = createApi({
    reducerPath: endpoints.tariff.api,
    baseQuery: baseQueryWithAuth,
    tagTypes: ['TariffChanged'],
    endpoints: (build) => ({
        getTariffs: build.query<TariffType[], void>({
            query: () => ({
                url: endpoints.tariff.search.all,
                method: "GET"
            }),
            providesTags: ['TariffChanged']
        }),
        createTariff: build.mutation<any, TariffFormType>({
            query: (tariff) => ({
                url: endpoints.tariff.create,
                method: "POST",
                body: tariff
            }),
            invalidatesTags: ['TariffChanged']
        }),
        deleteTariff: build.mutation<any, string>({
            query: (id) => ({
                url: endpoints.tariff.delete.replace(":id", id),
                method: "DELETE",
            }),
            invalidatesTags: ['TariffChanged']
        })
    })
})