import { useState } from "react";
import { Button } from "../../../../../components/button/Button";
import { ButtonStyleType } from "../../../../../components/button/Types";
import { Modal } from "../../../../../components/modal/Modal";
import { CreateTariffForm } from "./form/CreateTariffForm";

export const CreateTariff = () => {
    const [open, setOpen] = useState<boolean>(false);
    
    return <div>
        <Button text={"Создать"} 
            type={ButtonStyleType.Create} 
            onClick={() => setOpen(true)}
        />
        <Modal open={open} setOpen={setOpen}>
            <CreateTariffForm close={setOpen}/>
        </Modal>
    </div>;
}