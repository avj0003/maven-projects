package Models;

public class MathTopic {

    protected String topic_name;
    protected int topic_id;

    public String getTopicName() {
        return topic_name;
    }

    public int getTopicId() {
        return topic_id;
    }

    public MathTopic(int id, String name) {
        topic_id = id;
        topic_name= name;
    }
}
