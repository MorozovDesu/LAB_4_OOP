import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Group {

    private String groupName = "?";
    private final Cadres[] groupList = new Cadres[20];

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public Group clone() {
        try {
            Group clone = (Group) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void addCadres(Cadres newCadres) {
        if (newCadres.getGroup() == null) { //проверяем не работает ли кадр уже в какойто группе
            boolean isAdd = false; //успешно ли добавлен кадр?
            for (int i = 0; i < groupList.length; i++) {
                if (groupList[i] == null) { //если место в группе свободно
                    groupList[i] = newCadres; //добавляем на свободное место
                    newCadres.setGroup(this); //указываем что кадр теперь работает в этой группе
                    System.out.println("Cadr " + newCadres.getName() + " is added to the group: " + groupName);
                    isAdd = true;
                    break;
                }
            }
            try {
                if (!isAdd) {
                    throw new FullGroupException(); //если кадр не добавлен, так как нет места
                }
            } catch (FullGroupException e) {
                System.out.println("The group is full ");
            }
            //если этот студент уже есть в какойто группе
        } else
            System.out.println("Cadres " + newCadres.getName() + " already working in group: " + newCadres.getGroup().getGroupName());

    }

    public void addCadres() { // для интерактивного добавления рабочего
        int freePlace = -1; // cюда запишем номер свободного места
        for (int i = 0; i < groupList.length; i++) { //иещем есть ли свободное место
            if (groupList[i] == null) { //если место в группе свободно
                freePlace = i;
                System.out.println("Free place was found in group " + groupName + ". Please enter cadr ");
                break;
            }
        }
        if (freePlace < 0) { //если места не нашлось
            System.out.println("The group is full");
        } else { //если нашли место
            while (true) { //для повтора попытки добавления студента если был некорректный ввод данных
                Cadres st = new Worker();
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    System.out.println("Please enter Worker name (String):");
                    st.setName(reader.readLine());
                    System.out.println("Please enter Worker age (int):");
                    st.setAge(Integer.parseInt(reader.readLine()));
                    System.out.println("Please enter Worker job (String):");
                    st.setJob(reader.readLine());
                    groupList[freePlace] = st; //добавляем на свободное место
                    st.setGroup(this); //указываем что кадр теперь работает в этой группе
                    System.out.println("Student " + st.getName() + " is added to the group: " + groupName);
                    break;
                } catch (Exception e) { //если ввели некоректные данные предлагаем попробовать еще раз
                    System.out.println("Invalid enter, Worker is not added, try again? (y / n):");
                    try {
                        if (reader.readLine().equals("y")) {
                            System.out.println("Please enter cadr");
                        } else { //при любом вводе отличном от "y" заканчиваем попытки добавить кадра
                            break;
                        }
                    } catch (
                            Exception x) { //любой ввод будет коректным, но reader.readLine() считается опаснм потому обработаем
                        System.out.println("Invalid enter");
                        break;
                    }
                }
            }
        }
    }

    private static class FullGroupException extends Exception {
    }

    public void delCadres(String name) { //удаление по имени
        int countSt = 0;
        for (Cadres st : groupList) {  //пересчитаем сколько работников с таким именем в группе
            if (st != null && st.getName().equals(name)) {
                countSt++;
            }
        }
        if (countSt == 0) {
            System.out.println("No cadr " + name + " in " + groupName + " group"); //если студентов нет
        }
        if (countSt == 1) { //если один студент, то удаляем
            for (int i = 0; i < groupList.length; i++) {
                if (groupList[i] != null && groupList[i].getName().equals(name)) {
                    groupList[i].setGroup(null); //указываем что кадр теперь не работает ни в какой группе
                    groupList[i] = null; //удаляем работника
                    sortGroupListAfterDelCadr(); // убираем дырку null в массиве после удаления
                    System.out.println("Cadr " + name + " is deleted from the group" + groupName);
                    break;
                }
            }
        }
        if (countSt > 1) { //если больше одного
            System.out.println("Cadr " + name + " in group " + groupName + " more then 1!"); //сообщаем что кадров больше 1
            searchCadr(name); //
            System.out.println("Please use the method delCadr(int number)"); //просим удалить по номеру
        }
    }

    public void searchCadr(String name) { //поиск всех работников с таким именем
        boolean isFound = false; //найден ли хотябы один раотник с таким именем
        for (int i = 0; i < groupList.length; i++) {
            if (groupList[i] != null && groupList[i].getName().equals(name)) {
                System.out.println("Cadr " + name + " is found in group. List number is " + (i + 1) + ".");
                groupList[i].info();
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No Cadr " + name + " in " + groupName + " group"); //если не найден
        }
    }


    public void delCadres(int number) { //удаляем  по номеру в группе
        try {
            String bufferCadrName = groupList[number - 1].getName(); //запоминаем имя чтоб вывести сообщение после удаления
            groupList[number - 1].setGroup(null); //указываем что работник теперь не учится ни в какой группе
            groupList[number - 1] = null; //удаляем
            sortGroupListAfterDelCadr(); // убираем дырку null в массиве после удаления
            System.out.println("Student " + bufferCadrName + " is deleted from the group" + groupName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid student number");
        }
    }

    private void sortGroupListAfterDelCadr() { // убираем дырку null в массиве после удаления
        for (int i = 0; i < groupList.length - 1; i++) {//проходим по всем элементам кроме последнего
            if (groupList[i] == null && groupList[i + 1] != null) {
                groupList[i] = groupList[i + 1];
                groupList[i + 1] = null;
            }
        }
    }

    @Override
    public String toString() {
        return stListToString(groupList);
    }

    String stListToString(Cadres[] arr) { //выносим конвертацию массива в строку, та как она нужна в нескольких методах
        StringBuilder sb = new StringBuilder();
        sb.append("groupName=");
        sb.append(groupName);
        sb.append("\n");
        for (Cadres st : arr) {
            if (st == null) {
                break;
            }
            sb.append(st);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sortNameAndPrint() { //сортировка работников в алфавитном порядке + печать списка
        Cadres[] sortList = arrWithoutNull(groupList);
        //Arrays.sort(sortList);
        for (int i = 0; i < sortList.length - 1; i++) {
            for (int y = i + 1; y < sortList.length; y++) {
                if (sortList[i] != null && sortList[y] != null && sortList[i].getName().compareTo(sortList[y].getName()) > 0) {
                    Cadres buffer = sortList[i];
                    sortList[i] = sortList[y];
                    sortList[y] = buffer;
                }
            }
        }

        System.out.println("afterNameSort");
        System.out.println(stListToString(sortList));
    }

    private Cadres[] arrWithoutNull(Cadres[] arr) { //вспомогательный метод который вернет массив без null в конце, если они есть
        int i = 0;
        for (Cadres st : arr) {
            if (st == null) {
                break;
            }
            i++;
        }
        Cadres[] sortList = new Cadres[i];
        System.arraycopy(arr, 0, sortList, 0, i);
        return sortList;
    }
}
