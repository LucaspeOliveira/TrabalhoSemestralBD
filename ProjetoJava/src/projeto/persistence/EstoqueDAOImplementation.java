package projeto.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projeto.model.EntityDistribuidora;
import projeto.model.EntityEstoque;
import projeto.model.EntityFilial;
import projeto.model.EntityFuncionario;
import projeto.model.EntityRoupa;

public class EstoqueDAOImplementation implements EstoqueDAO {

    private GenericDAO gDao;
    public EstoqueDAOImplementation(GenericDAO gDao){
        this.gDao = gDao;
    }

    @Override
    public List<EntityRoupa> listarRoupas() throws ClassNotFoundException, SQLException {
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM roupa";
        PreparedStatement ps;
        List<EntityRoupa> listaRoupas = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                EntityRoupa roupa = new EntityRoupa();
                roupa.setId(rs.getInt("id"));
                roupa.setTipo(rs.getString("tipoRoupa"));
                roupa.setIdEstoque(rs.getInt("idEstoque"));
                roupa.setMarca(rs.getString("marca"));
                roupa.setTamanho(rs.getString("tamanho"));
                roupa.setGenero(rs.getString("genero"));
                roupa.setQuantidade(rs.getInt("quantidade"));
                listaRoupas.add(roupa);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaRoupas;
    }

    @Override
    public EntityRoupa procurarRoupa(int idRoupa) throws SQLException, ClassNotFoundException, IllegalArgumentException {
        Connection con = gDao.getConnection();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM roupa WHERE id = ?");
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, idRoupa);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            EntityRoupa roupa = new EntityRoupa();
            roupa.setId(rs.getInt("id"));
            roupa.setTipo(rs.getString("tipoRoupa"));
            roupa.setIdEstoque(rs.getInt("idEstoque"));
            roupa.setMarca(rs.getString("marca"));
            roupa.setTamanho(rs.getString("tamanho"));
            roupa.setGenero(rs.getString("genero"));
            roupa.setQuantidade(rs.getInt("quantidade"));
            return roupa;
        }
        rs.close();
        ps.close();
        con.close();
        throw new IllegalArgumentException("Objeto não encontrado");
    }

    @Override
    public void atualizarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "UPDATE roupa SET id = ?, tipoRoupa = ?, idEstoque = ?, marca = ?, tamanho = ?, genero = ?, quantidade = ? WHERE codigo = ?";
        PreparedStatement ps = con.prepareStatement(sql.toString());
        ps.setInt(1, roupa.getId());
        ps.setString(2, roupa.getTipo());
        ps.setInt(3, roupa.getIdEstoque());
        ps.setString(4, roupa.getMarca());
        ps.setString(5, roupa.getTamanho());
        ps.setString(6, roupa.getGenero());
        ps.setInt(7, roupa.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void excluirRoupa(int idRoupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "DELETE roupa WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idRoupa);
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public void adicionarRoupa(EntityRoupa roupa) throws SQLException, ClassNotFoundException {
        Connection con = gDao.getConnection();
        String sql = "INSERT INTO roupa VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, roupa.getId());
        ps.setString(2, roupa.getTipo());
        ps.setInt(3, roupa.getIdEstoque());
        ps.setString(4, roupa.getMarca());
        ps.setString(5, roupa.getTamanho());
        ps.setString(6, roupa.getGenero());
        ps.setInt(7, roupa.getQuantidade());
        ps.execute();
        ps.close();
        con.close();
    }

    @Override
    public EntityEstoque preencherEstoque() throws SQLException, ClassNotFoundException {
        EntityEstoque ent = new EntityEstoque();
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM estoque";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ent.setId(rs.getInt("id"));
            ent.setIdFilial(rs.getInt("idFilial"));
            rs.close();
            ps.close();
            con.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return ent;
    }

    @Override
    public EntityFilial preencherFilial() throws SQLException, ClassNotFoundException {
        EntityFilial fili = new EntityFilial();
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM filialLoja";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            fili.setId(rs.getInt("id"));
            fili.setIdDistribuidora(rs.getInt("idDist"));
            rs.close();
            ps.close();
            con.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return fili;
    }

    @Override
    public EntityDistribuidora preencherDistribuidora() throws SQLException, ClassNotFoundException {
        EntityDistribuidora dist = new EntityDistribuidora();
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM distribuidora";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            dist.setId(rs.getInt("id"));
            dist.setTipoRoupa(rs.getString("tipoRoupaDist"));
            rs.close();
            ps.close();
            con.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return dist;
    }

    @Override
    public EntityFuncionario preencherFuncionario() throws SQLException, ClassNotFoundException {
        EntityFuncionario func = new EntityFuncionario();
        Connection con = gDao.getConnection();
        String sql = "SELECT * FROM distribuidora";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            func.setId(rs.getInt("id"));
            func.setNome(rs.getString("nome"));
            func.setIdEstoque(rs.getInt("idEstoque"));
            rs.close();
            ps.close();
            con.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return func;
    }
}
