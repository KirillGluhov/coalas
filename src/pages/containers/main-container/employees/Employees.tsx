import { CreateWrapper } from "../../../../components/forms/create/CreateWrapper";
import { InnerList } from "../../../../components/inner-list/InnerList";
import { userAPI } from "../../../../store/services/userService";
import { CreateEmployeeForm } from "./create-employee/CreateEmployeeForm";
import { Employee } from "./employee/Employee";

export const Employees = () => {
    const {data: employees, isLoading, error} = userAPI.useGetEmployeesQuery();

    return <InnerList
        title={"Сотрудники"} 
        createButton={<CreateWrapper Form={CreateEmployeeForm}/>} 
        isLoading={isLoading} 
        error={error}
        mapper={employees?.map(employee => <Employee employee={employee} key={employee.id}/>) ?? []} 
    />
}