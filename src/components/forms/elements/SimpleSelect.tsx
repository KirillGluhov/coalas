import { Controller, FieldValues } from "react-hook-form";
import { SimpleSelectType } from "./Types";
import { useTheme } from "../../../providers/theme/useTheme";
import Arrow from '../../../assets/icons/arrowdown.svg';
import { themes } from "../../../providers/theme/ThemeProvider";
import styles from './Elements.module.scss';
import externStyles from '../../../pages/containers/simple-container/login/form/LoginForm.module.scss';

export const SimpleSelect = <T extends FieldValues>({name, control, title, options, isRequired}: SimpleSelectType<T>) => {
    const {theme} = useTheme();
    
    return <Controller
        name={name}
        control={control}
        rules={{required: `Выберите ${title}`}}
        render={({field, fieldState}) => (
            <div className={`${externStyles.inputWithError} ${styles.selectWrapper}`}>
                <span className={styles.placeholder}>{`${title} ${isRequired ? '*' : ''}`}</span>
                <select 
                    {...field} 
                    onChange={(e) => {
                        let value = e.target.value;
                        field.onChange(value);
                    }}
                    className={`${externStyles.input} ${styles.select} ${theme === themes.light ? externStyles.light : externStyles.dark}`}
                >
                    <option 
                        key={""} 
                        value={""} 
                        className={`${theme === themes.light ? styles.light : styles.dark}`}
                    >
                        {""}
                    </option>
                    {
                        options.map((option) => (
                            <option 
                                key={option.id} 
                                value={option.id} 
                                className={`${theme === themes.light ? styles.light : styles.dark}`}
                            >
                                {option.value}
                            </option>
                        ))
                    }
                </select>
                <img src={Arrow} alt="arrow" className={styles.arrow} />
                <span className={externStyles.error}>{fieldState.error?.message}</span>
            </div>
        )}
    />
}