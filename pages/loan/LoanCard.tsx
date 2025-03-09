export const LoanCard = () => {

return <View style={styles.form}>
            <View style={styles.row}>
                <Select 
                    title="Кредит"
                    change={setRole}
                />
            </View>
            { 
                <View style={styles.operations}>
                    <View style={styles.row}>
                        {operations.map(operation => <FielView 
                            placeholder="Дата"
                            onChange={onChangeName}
                        />
                        <FieldView 
                            placeholder="Статус"
                            onChange={onChangeSurname}
                            
                        />
                        <FieldView 
                            placeholder="Баланс"
                            onChange={onChangeSurname}
                            
                        />
                    </View>
                    <View style={styles.row}>
                        <Select 
                            title="Пол"
                            placeholder="Выберите пол"
                            change={setGender}
                            items={[
                                { label: 'Мужской', value: 'male' },
                                { label: 'Женский', value: 'female' },
                            ]}
                            value={gender}
                        />
                        <Select 
                            title="* Должность"
                            placeholder="Выберите должность"
                            change={setPosition}
                            items={positions}
                            value={position}
                        />
                    </View>
                    <View style={styles.row}>
                        <DatePicker
                            title="Дата рождения"
                            value={birthDate}
                            change={setBirthDate}
                            placeholder="Выберите дату рождения"
                        />
                    </View>)
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Пароль"
                            onChange={onChangePassword}
                            formater={formatPassword}
                            setSpecificError={setPasswordError}
                            value={password}
                            specificError={isPasswordError}
                            inputMode="text"
                            keyboardType="default"
                            errorText="Длина не меньше 1"
                        />
                        <InputWithError
                            placeholder="Телефон"
                            onChange={onChangePhone}
                            formater={formatPhone}
                            setSpecificError={setPhoneError}
                            value={phone}
                            specificError={isPhoneError}
                            inputMode="tel"
                            errorText="Длина от 8 до 18"
                            keyboardType="phone-pad"
                        />
                        <InputWithError
                            placeholder="Email"
                            onChange={onChangeEmail}
                            formater={formatEmail}
                            setSpecificError={setEmailError}
                            value={email}
                            specificError={isEmailError}
                            inputMode="email"
                            errorText="Должен иметь формат как у email"
                            keyboardType="email-address"
                        />
                    </View>
                    <View style={styles.row}>
                        <CustomButton 
                            title="Зарегистрироваться" 
                            isAccent={true} 
                            onClick={handleEmployeeRegistration}
                            isError={isError}
                        ></CustomButton>
                    </View>
                </View>
                : 
                role === "client" ? 
                <View style={styles.form}>
                    <View style={styles.row}>
                    <InputWithError 
                            placeholder="* Имя"
                            onChange={onChangeName}
                            formater={formatPassword}
                            setSpecificError={setNameError}
                            value={name}
                            specificError={isNameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="* Фамилия"
                            onChange={onChangeSurname}
                            formater={formatPassword}
                            setSpecificError={setSurnameError}
                            value={surname}
                            specificError={isSurnameError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                        <InputWithError 
                            placeholder="Отчество"
                            onChange={onChangePatronic}
                            formater={formatPassword}
                            setSpecificError={setPatronicError}
                            value={patronic}
                            specificError={isPatronicError}
                            inputMode="text"
                            errorText="Не пустой"
                            keyboardType="default"
                        />
                    </View>
                    <View style={styles.row}>
                    <InputWithError
                            placeholder="Телефон"
                            onChange={onChangePhone}
                            formater={formatPhone}
                            setSpecificError={setPhoneError}
                            value={phone}
                            specificError={isPhoneError}
                            inputMode="tel"
                            errorText="Длина от 8 до 18"
                            keyboardType="phone-pad"
                        />
                        <InputWithError
                            placeholder="Email"
                            onChange={onChangeEmail}
                            formater={formatEmail}
                            setSpecificError={setEmailError}
                            value={email}
                            specificError={isEmailError}
                            inputMode="email"
                            errorText="Должен иметь формат как у email"
                            keyboardType="email-address"
                        />
                    </View>}
                    <View style={styles.row}>
                        <Select 
                            title="Пол"
                            placeholder="Выберите пол"
                            change={setGender}
                            items={[
                                { label: 'Мужской', value: 'male' },
                                { label: 'Женский', value: 'female' },
                            ]}
                            value={gender}
                        />
                        <DatePicker
                            title="Дата рождения"
                            value={birthDate}
                            change={setBirthDate}
                            placeholder="Выберите дату рождения"
                        />
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Пароль"
                            onChange={onChangePassword}
                            formater={formatPassword}
                            setSpecificError={setPasswordError}
                            value={password}
                            specificError={isPasswordError}
                            inputMode="text"
                            keyboardType="default"
                            errorText="Длина не меньше 1"
                        />
                    </View>
                    <View style={styles.row}>
                        <Text style={styles.passport}>Паспорт</Text>
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Серия"
                            onChange={onChangeSeries}
                            formater={formatSeries}
                            setSpecificError={setSeriesError}
                            value={series}
                            specificError={isSeriesError}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У серии формат цц цц"
                        />
                        <InputWithError 
                            placeholder="* Номер"
                            onChange={onChangeNumber}
                            formater={formatPassportNumber}
                            setSpecificError={setPassportNumberError}
                            value={passportNumber}
                            specificError={isPassportNumberError}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У номера 6 цифр"
                        />
                    </View>
                    <View style={styles.row}>
                        <DatePicker
                            title="* Дата выдачи"
                            value={issueDate}
                            change={onChangeIssueDate}
                            placeholder="Выберите дату выдачи"
                        />
                    </View>
                    <View style={styles.row}>
                        <InputWithError 
                            placeholder="* Код выдачи"
                            onChange={onChangeDepartmentCode}
                            formater={formatDepartmentCode}
                            setSpecificError={setDepartmentCodeError}
                            value={departmentCode}
                            specificError={isDepartmentCode}
                            inputMode="numeric"
                            keyboardType="numeric"
                            errorText="У кода 6 цифр"
                        />
                        <InputWithError 
                            placeholder="* Кем выдан"
                            onChange={onChangeDepartmentName}
                            formater={formatPassword}
                            setSpecificError={setDepartmentNameError}
                            value={departmentName}
                            specificError={isDepartmentName}
                            inputMode="text"
                            keyboardType="default"
                            errorText="Хотя бы один символ"
                        />
                    </View>
                    <View style={styles.row}>
                        <CustomButton 
                            title="Зарегистрировать" 
                            isAccent={true} 
                            onClick={handleClientRegistration}
                            isError={isError}
                        ></CustomButton>
                    </View>
                </View> : 
                null
            }
        </View>
}