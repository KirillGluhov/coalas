import { FC } from 'react';
import styles from './Toggle.module.scss';
import { ToggleType } from './Types';

export const Toggle: FC<ToggleType> = ({ value, onChange }) => {
    return (
    <label className={styles.switch} htmlFor="toggler">
      <input
        id="toggler"
        type="checkbox"
        onClick={onChange}
        checked={value}
        readOnly
      />
      <span className={styles.slider} />
    </label>
  )
}