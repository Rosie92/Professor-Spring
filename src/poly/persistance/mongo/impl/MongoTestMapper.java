package poly.persistance.mongo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import poly.persistance.mongo.IMongoTestMapper;

@Component("MongoTestMapper")
public class MongoTestMapper implements IMongoTestMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".createCollection Start!");

		boolean res = false;

		// ������ ��ϵ� �÷��� �̸��� �����ϴ��� üũ�ϰ�, �����ϸ� ���� �÷��� ������
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // ���� �÷��� ����

		}

		// �÷��� ���� �� �ε��� ����, �����񿡼� ������ �������� ��Ŀ� �°� �ε����� �ݵ�� ��������!
		// ������ ���� ���� ������ �������� ������, �ּ� 10���� �̻� ������ ����� �ӵ��� �� 10�� �̻� �߻���
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("user_id", 1), "testIdx");

		res = true;

		log.info(this.getClass().getName() + ".createCollection End!");

		return res;
	}
	
	

}

