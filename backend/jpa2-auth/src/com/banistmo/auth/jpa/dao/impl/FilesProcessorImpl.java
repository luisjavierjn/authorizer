package com.banistmo.auth.jpa.dao.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.banistmo.auth.jpa.dao.IFilesProcessor;
import com.banistmo.auth.jpa.entity.Merchant;
import com.banistmo.auth.jpa.util.Constant;
import com.banistmo.auth.jpa.util.FileProcessorUtils;
import com.banistmo.auth.web.dto.DataTransactionModel;

@Repository
@Component("processor")
public class FilesProcessorImpl implements IFilesProcessor {
	FileProcessorUtils validateData = new FileProcessorUtils();
//	DataTransactionModel merModel = new DataTransactionModel();
	Merchant merModel = new Merchant();
	Object[] objetoMerchant = null;
	ArrayList<STRUCT> dataArray = new ArrayList<STRUCT>();
	CallableStatement cs = null;
	EntityManager em = null;
	InitialContext ctx = null;
	DataSource ds = null;

	StructDescriptor sd = null;
	STRUCT dataStruct = null;

	@SuppressWarnings("deprecation")
	@Override
	public boolean processIncomingFile(InputStream dataIn) {
		BufferedReader br = new BufferedReader(new InputStreamReader(dataIn));
		try {
			String line;
			boolean headerChecked = false;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (validateData.checkFileRows(line)) {
					if (headerChecked == false) {
						headerChecked = validateData.checkHeaderFile(line);
						System.out
								.println("---------> File's header was checked.");
					} else {
						// List<Object> objeto =
						// validateData.checkFileContent(line);

						System.out.println("Data ok");
						// saveMerchantLot(validateData.checkFileContent(line));
						merModel = validateData.checkFileContent(line);
						saveMerchant(merModel);

//						// llenar el objeto
//						objetoMerchant = new Object[] {
//								merModel.getMERCHANT_ID(),
//								merModel.getMERCHANT_NAME(),
//								merModel.getACCOUNT_HOGAN_NO(),
//								merModel.getRUC_NO(),
//								merModel.getCHECK_DIGIT(),
//								merModel.getPERCENTAGE_RETENTION(),
//								merModel.getCOMMISSION(),
//								merModel.getCOMMISSION_ITBMS(),
//								merModel.getCHANNEL() };
//
//						ctx = new InitialContext();
//						ds = (DataSource) ctx.lookup("sg-auth-ds");
//
//						Connection cnn = (Connection) com.ibm.websphere.rsadapter.WSCallHelper
//								.getNativeConnection(ds.getConnection());
//						cnn = cnn.unwrap(oracle.jdbc.OracleConnection.class);
//						sd = StructDescriptor.createDescriptor("MERCHANT_REC",
//								cnn);
//
//						dataStruct = new STRUCT(sd, cnn, objetoMerchant);
//
//						cnn.close();
//
//						dataArray.add(dataStruct);

					}
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		finally {
//			setMerchantType(dataArray);
//		}
		return false;
	}

//	public void setMerchantType(ArrayList<STRUCT> dataArrayStruct) {
//		try {
//			em = SGEntityManagerFactory.getInstance().getManager(
//					Constant.SESSION_TYPE);
//			Connection cnn = (Connection) com.ibm.websphere.rsadapter.WSCallHelper
//					.getNativeConnection(ds.getConnection());
//			cnn = cnn.unwrap(oracle.jdbc.OracleConnection.class);
//			em.getTransaction().begin();
//
//			cs = cnn.prepareCall("{call AUTHORIZER_SERVICES.SAVE_MERCHANT_LOT(?,?)}");
//			cs.registerOutParameter(1, OracleTypes.VARCHAR);
//			cs.setObject(2, dataArrayStruct);
//			cs.execute();
//
//			String mesageError = cs.getString(1);
//			if (mesageError != null) {
//				throw new Exception("Error al guardarle.");
//			}
//
//			em.getTransaction().commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				if (cs != null)
//					cs.close();
//				if (em != null)
//					em.close();
//			} catch (SQLException e) {
//				System.out
//						.println("Ocurrio un error inesperado al momento de almacenar los datos."
//								+ e);
//			}
//		}
//
//	}
//
	public void saveMerchant(Merchant modelo) {
		EntityManager em = null;
		try {
			em = SGEntityManagerFactory.getInstance().getManager(
					Constant.SESSION_TYPE);

			em.getTransaction().begin();

			em.persist(modelo);

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
//
//	@SuppressWarnings({ "null" })
//	public void saveMerchantLot(Merchant model) {
//		CallableStatement cs = null;
//		javax.persistence.EntityManager em = null;
//		// Object[] objetoMerchant = null;
//		oracle.sql.STRUCT dataStruct = null;
//		// ArrayList<STRUCT> dataArray = new ArrayList<STRUCT>();
//		ArrayList dataArray = new ArrayList();
//		try {
//			InitialContext ctx = new InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("sg-auth-ds");
//
//			em = SGEntityManagerFactory.getInstance().getManager(
//					Constant.SESSION_TYPE);
//
//			Connection cnn = (Connection) com.ibm.websphere.rsadapter.WSCallHelper
//					.getNativeConnection(ds.getConnection());
//
//			cnn = cnn.unwrap(oracle.jdbc.OracleConnection.class);
//
//			@SuppressWarnings("deprecation")
//			StructDescriptor sd = StructDescriptor.createDescriptor(
//					"MERCHANT_REC", cnn);
//
//			// objetoMerchant = new Object[] { model.getMerchantId(),
//			// model.getMerchantName(), model.getAccountHoganNo(),
//			// model.getRucNo(), model.getCheckDigit(),
//			// model.getPercentageRetention(), model.getCommission(),
//			// model.getCommissionItbms(), model.getChannel(),
//			// model.getStatus() };
//
//			dataStruct = new oracle.sql.STRUCT(sd, cnn, objetoMerchant);
//
//			dataArray.add(dataStruct);
//
//			cs = cnn.prepareCall("call AUTHORIZER_SERVICES.SAVE_MERCHANT_LOT(?,?)");
//			cs.registerOutParameter(1, OracleTypes.VARCHAR);
//			cs.setObject(2, dataArray);
//			cs.execute();
//
//			String mesageError = cs.getString(1);
//			if (mesageError != null) {
//				throw new Exception("Error al guardarle.");
//			}
//
//			cnn.close();
//			em.getTransaction().commit();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//			e.printStackTrace();
//		} finally {
//			try {
//				if (cs != null)
//					cs.close();
//				if (em != null)
//					em.close();
//			} catch (SQLException e) {
//				System.out
//						.println("Ocurrio un error inesperado al momento de almacenar los datos."
//								+ e);
//			}
//		}
//	}

}
