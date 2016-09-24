package in.cw.csense.mongo.sequence.generator;

public interface CloudSequenceDao {
	int getNextSequenceId(String key) throws SequenceException;
}