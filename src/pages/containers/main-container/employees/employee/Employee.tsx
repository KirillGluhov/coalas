import { FC } from "react";
import { EmployeePropsType } from "./Types";
import { InnerCard } from "../../../../../components/inner-card/InnerCard";
import { getFullName, getGenderLogo, getPosition } from "../../../../../utils";
import { userAPI } from "../../../../../store/services/userService";
import styles from './Employee.module.scss';
import { DeleteWrapper } from "../../../../../components/forms/delete/DeleteWrapper";

export const Employee: FC<EmployeePropsType> = ({employee}) => {

    const {data: position} = userAPI.useGetPositionQuery(employee?.positionId ?? "", {skip: !employee?.positionId});
    const [deleteEmployee, _] = userAPI.useBlockUserMutation();

    return <InnerCard id={employee.id}>
        <>
            <div className={styles.row}>
                <div className={styles.nameAndGender}>
                    <h2>{getFullName(employee)}</h2>
                    <img src={getGenderLogo(employee.gender)} className={styles.gender}/>
                </div>
                <div>
                    {
                        employee.blocked ? 
                        <p className={styles.dangerText}>Заблокирован</p> : 
                        <DeleteWrapper 
                            id={employee.id} 
                            title={"сотрудника"} 
                            deleteEntity={deleteEmployee}
                        />
                    }
                </div>
            </div>
            <div className={styles.row}>
                <div className={styles.position}>
                    {getPosition(position)}
                </div>
                <div className={styles.col}>
                    <p className={styles.email}>{employee.email}</p>
                    <p className={styles.phone}>{employee.phone}</p>
                </div>
            </div>
        </>
    </InnerCard>
}