# Лабораторная работа №6. Уведомления
## Выполнила: Донченко Вика, ИСП-211о
## Язык программирования: Java

### 1. Функции приложения
Приложение предназначено для создания, просмотра и удаления напоминаний о важных событиях. Основные функции:
- Добавление нового напоминания с заголовком, текстом и сроком выполнения.
- Просмотр списка всех созданных напоминаний.
- Удаление выбранного напоминания.
- Отправка уведомлений в заданное время.

### 2. Главное меню
Главный экран (MainActivity) содержит следующие элементы:

Кнопка "Добавить напоминание" для доступа к форме создания нового напоминания.
Список текущих напоминаний.
Иконки для удаления напоминаний.

![1](https://github.com/user-attachments/assets/e1c70685-c9d5-41aa-aacf-bf3e10f6c0ad)

### 3. Добавление напоминания
При нажатии на кнопку "Добавить напоминание" приложение открывает экран добавления нового напоминания. В этом экране можно:
- Ввести название напоминания.
- Ввести текст напоминания.
- Выбрать дату и время с помощью диалогов выбора даты и времени.
  
'btnSetDateTime.setOnClickListener(v -> {
    showDateTimePicker();
});'

![2](https://github.com/user-attachments/assets/e97e1d6c-9d7c-4f09-a766-9a163dd8e5e3)

![дата](https://github.com/user-attachments/assets/f17d8af9-41fc-48be-9fbe-d5df02de098f)

![время](https://github.com/user-attachments/assets/51ebd6d6-921b-4be6-8360-3efc29ace18d)

- При нажатии на кнопку "Сохранить" приложение проверяет все поля и сохраняет напоминание в базу данных, а также планирует уведомление на выбранное время.
  
'private void saveReminder() {
    String title = editTextTitle.getText().toString();
    String text = editTextText.getText().toString();
    if (title.isEmpty() || text.isEmpty() || dateTime == 0) {
        Toast.makeText(this, "Пожалуйста, заполните все поля и выберите дату и время", Toast.LENGTH_SHORT).show();
        return;
    }
}'

![4](https://github.com/user-attachments/assets/34677551-455e-4117-b724-b1c3dde166f9)

### 4. Просмотр и удаление напоминаний
На главном экране отображается список всех напоминаний. Каждое напоминание показывает название, текст и дату/время завершения. При нажатии на кнопку удаления, напоминание удаляется из базы данных, и список обновляется.

'@Override
public void onDeleteClick(Reminder reminder) {
    dbHelper.deleteReminder(reminder.getReminderId());
    loadReminders();
    Toast.makeText(MainActivity.this, "Напоминание удалено", Toast.LENGTH_SHORT).show();
}'

![3](https://github.com/user-attachments/assets/c1479335-ce68-419f-bcd9-60a28888990e)

![5](https://github.com/user-attachments/assets/cb2bc923-1163-40e9-85f1-152f17682eb5)

