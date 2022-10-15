package br.com.fiap.tads.ddd.coffee.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tads.ddd.coffee.model.Coffee;
import jakarta.validation.Valid;

public class CoffeeRepository extends Repository {

	public static List<Coffee> findAll() {

		String sql = "SELECT * FROM DDD_COFFEE";

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Coffee> cafes = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {

					Coffee cafe = new Coffee();

					cafe.setId(rs.getLong("ID"));
					cafe.setNome(rs.getString("NOME"));
					cafe.setPreco(rs.getDouble("PRECO"));
					cafe.setDataDeFabricacao(rs.getDate("DATA_FABRICACAO").toLocalDate());
					cafe.setDataDeValidade(rs.getDate("DATA_VALIDADE").toLocalDate());

					cafes.add(cafe);
				}
			} else {
				System.out.println("Não foram encontrados registros na tabela do banco de dados.");
			}

		} catch (SQLException e) {
			System.out.println("Não foi possível consultar a listagem de cafés: " + e.getMessage());
		}

		return cafes;
	}

	public static Coffee save(Coffee coffee) {

		// @formatter:off
 		String sql = "begin INSERT INTO ddd_coffee ("
				+ "    id,"
				+ "    nome,"
				+ "    preco,"
				+ "    data_fabricacao,"
				+ "    data_validade"
				+ ") VALUES ("
				+ "    SQ_COFFEE.nextval,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?"
				+ ") "
				+ " returning id into ?; end;";
 		// @formatter:on

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);

			cs.setString(1, coffee.getNome());
			cs.setDouble(2, coffee.getPreco());
			cs.setDate(3, Date.valueOf(coffee.getDataDeFabricacao()));
			cs.setDate(4, Date.valueOf(coffee.getDataDeValidade()));

			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			cs.executeUpdate();
			coffee.setId((long) cs.getInt(5));

			return coffee;

		} catch (SQLException e) {
			System.out.println("Erro para salvar o cafe no banco de dados: " + e.getMessage());
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statment: " + e.getMessage());
				}
		}

		return null;

	}

	public static boolean delete(Long coffeeId) {

		Coffee coffee = null;
		String sql = "DELETE FROM DDD_COFFEE where ID = ?";
		PreparedStatement ps = null;

		coffee = findById(coffeeId);

		if (coffee == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);

			ps.setLong(1, coffeeId);

			ps.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("Erro para deletar o cafe no banco de dados: " + e.getMessage());
		} finally {

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statment: " + e.getMessage());
				}
		}

		return false;
	}

	public static Coffee findById(Long id) {
		String sql = "SELECT * FROM DDD_COFFEE where id = ?";

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {

			ps = getConnection().prepareStatement(sql);

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				Coffee cafe = new Coffee();
				while (rs.next()) {
					cafe.setId(rs.getLong("ID"));
					cafe.setNome(rs.getString("NOME"));
					cafe.setPreco(rs.getDouble("PRECO"));
					cafe.setDataDeFabricacao(rs.getDate("DATA_FABRICACAO").toLocalDate());
					cafe.setDataDeValidade(rs.getDate("DATA_VALIDADE").toLocalDate());
				}

				return cafe;
			}

		} catch (SQLException e) {
			System.out.println("Erro para consultar o cafe no banco de dados: " + e.getMessage());
		} finally {

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Prepared Statment: " + e.getMessage());
				}

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o ResultSet: " + e.getMessage());
				}

		}

		return null;

	}

	public static Coffee update(@Valid Coffee cafe) {

		String sql = "UPDATE DDD_COFFEE set NOME=?, PRECO =? , DATA_FABRICACAO= ?, DATA_VALIDADE= ? where id = ?";

		CallableStatement cs = null;

		try {
			cs = getConnection().prepareCall(sql);

			cs.setString(1, cafe.getNome());
			cs.setDouble(2, cafe.getPreco());
			cs.setDate(3, Date.valueOf(cafe.getDataDeFabricacao()));
			cs.setDate(4, Date.valueOf(cafe.getDataDeValidade()));
			cs.setLong(5, cafe.getId());
			cs.executeUpdate();

			return cafe;

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o cafe no banco de dados: " + e.getMessage());
		} finally {
			if (cs != null)
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Não foi possível fechar o Callable Statment: " + e.getMessage());
				}
		}

		return null;
	}

}
