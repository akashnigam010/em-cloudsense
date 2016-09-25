package in.cw.csense.app.mongo.sequence.generator;

import in.cw.csense.app.mongo.sequence.generator.SequenceException;

public interface CloudSequenceDao {
	int getNextSequenceId(String key) throws SequenceException;
}