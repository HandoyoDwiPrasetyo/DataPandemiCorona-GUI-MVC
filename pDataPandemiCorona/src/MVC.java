public class MVC {
    View view = new View();
    DataPandemi dataPandemi = new DataPandemi();
    DAO dao = new DAO();
    Controller controller = new Controller(dataPandemi, view, dao);
}
