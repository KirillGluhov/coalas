export const formatPhone = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) =>
{
    const numbersOnly = text.replace(/\D/g, '').slice(0, 18);
    if (numbersOnly.length < 8)
    {
        setError(true);
    }
    else
    {
        setError(false)
    }

    if ((numbersOnly.length >= 8 && numbersOnly.length <= 18) || numbersOnly.length === 0)
    {
        setError(false);
    }
    else
    {
        setError(true)
    }

    return numbersOnly.length > 0 ? `+${numbersOnly}` : '';
};

export const formatEmail = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => {
    const emailRegex = /^((?:[A-Za-z0-9!#$%&'*+\-\/=?^_`{|}~]|(?<=^|\.)"|"(?=$|\.|@)|(?<=".*)[ .](?=.*")|(?<!\.)\.){1,64})(@)((?:[A-Za-z0-9.\-])*(?:[A-Za-z0-9])\.(?:[A-Za-z0-9]){2,})$/;

    if (emailRegex.test(text) || text.length === 0)
    {
        setError(false);
    }
    else
    {
        setError(true)
    }

    return text;
}

export const formatPassword = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) =>
{
    if (text.length <= 0)
    {
        setError(true)
    }
    else
    {
        setError(false)
    }
    return text;
}

export const formatSeries = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => {
    const cleaned = text.replace(/\D/g, '').slice(0, 4);
    if (cleaned.length < 4 && cleaned.length !== 0)
    {
        setError(true)
    }
    else 
    {
        setError(false);
    }
    const formatted = cleaned.replace(/(\d{2})(\d{2})?/, "$1 $2").trim(); 
    return formatted
}

export const formatPassportNumber = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => {
    const formatted = text.replace(/\D/g, '').slice(0, 6);

    if (formatted.length < 6 && formatted.length !== 0)
    {
        setError(true)
    }
    else 
    {
        setError(false);
    }
    
    return formatted
}

export const formatDepartmentCode = (text: string, setError: React.Dispatch<React.SetStateAction<boolean>>) => {
    const cleaned= text.replace(/\D/g, '').slice(0, 6);
    
    if (cleaned.length < 6 && cleaned.length !== 0)
    {
        setError(true)
    }
    else 
    {
        setError(false);
    }
    const formatted = cleaned.replace(/(\d{3})(\d{3})/, "$1-$2");
    return formatted
}