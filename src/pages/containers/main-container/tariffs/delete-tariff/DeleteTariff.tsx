import { FC, useState } from "react"
import { Button } from "../../../../../components/button/Button"
import { ButtonStyleType } from "../../../../../components/button/Types"
import { DeleteTariffType } from "./Types"
import { Modal } from "../../../../../components/modal/Modal"
import { DeleteTariffForm } from "./form/DeleteTariffForm"

export const DeleteTariff: FC<DeleteTariffType> = ({id}) => {
    const [open, setOpen] = useState<boolean>(false);

    return <div>
        <Button 
            text={"Удалить"} 
            type={ButtonStyleType.Delete}
            onClick={() => setOpen(true)}
        />
        <Modal open={open} setOpen={setOpen}>
            <DeleteTariffForm close={setOpen} id={id}/>
        </Modal>
    </div>
}