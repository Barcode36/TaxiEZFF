package services.sql;

import models.Direccion;
import resources.Statics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DireccionSQL {


    private final Connection connection;

    public DireccionSQL() {
        this. connection = Statics.getConnections();
    }

    public boolean insertarDireccion(Direccion direccion)  {
        String query = "INSERT INTO `direccion` (`idDireccion`, `calle`, `colonia`, `numInt`, `numExt`) VALUES (?, ?,?,?,?); ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,direccion.getIdDireccion());
            preparedStatement.setString(2,direccion.getCalle());
            preparedStatement.setString(3,direccion.getColonia());
            preparedStatement.setString(4,direccion.getNumInt());
            preparedStatement.setString(5,direccion.getNumExt());

            int i = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public boolean actualizar(Direccion direccion)  {
        String query = "UPDATE direccion SET calle = ?,colonia = ?, numInt = ?,numExt = ? WHERE direccion.idDireccion = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,direccion.getCalle());
            preparedStatement.setString(2,direccion.getColonia());
            preparedStatement.setString(3,direccion.getNumInt());
            preparedStatement.setString(4,direccion.getNumExt());
            preparedStatement.setInt(5,direccion.getIdDireccion());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public int getLastId() throws SQLException {

        int idDireccion = -1;
        String query=" SELECT MAX(idDireccion) FROM Direccion";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            idDireccion = resultSet.getInt(1) + 1;
            if( idDireccion == 0){
                idDireccion = 1;
            }
        }

        return idDireccion;
    }

}
