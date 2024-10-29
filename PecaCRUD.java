import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PecaCRUD {
    Connection connect = null;

    public PecaCRUD() {
    }

    public Connection conexaoBD() {
        return ConnectionMySQL.getConnectionMySQL();
    }

    public void createPeca(Peca peca) {
        String sql = "INSERT INTO pecas VALUES (?,?,?,?)";

        try {
            Connection connect = this.conexaoBD();

            try {
                PreparedStatement stmt = connect.prepareStatement(sql);

                try {
                    stmt.setInt(1, peca.getCodigo());
                    stmt.setString(2, peca.getNome());
                    stmt.setDouble(3, peca.getPreco());
                    stmt.setString(4, peca instanceof PecaGenuina ? "Genuína" : "Segunda linha");
                    stmt.executeUpdate();
                    System.out.println("Peça criada com sucesso!");
                } catch (Throwable var9) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var10) {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            System.out.println("Peça não criada: " + e.getMessage());
        }

    }

    public boolean existeCodigo(int codigo) {
        String sql = "SELECT COUNT(*) FROM pecas WHERE codigo = ?";

        try {
            Connection connect = this.conexaoBD();

            label83: {
                boolean var6;
                try {
                    PreparedStatement stmt = connect.prepareStatement(sql);

                    label85: {
                        try {
                            stmt.setInt(1, codigo);
                            ResultSet rs = stmt.executeQuery();
                            if (rs.next()) {
                                var6 = rs.getInt(1) != 0;
                                break label85;
                            }
                        } catch (Throwable var9) {
                            if (stmt != null) {
                                try {
                                    stmt.close();
                                } catch (Throwable var8) {
                                    var9.addSuppressed(var8);
                                }
                            }

                            throw var9;
                        }

                        if (stmt != null) {
                            stmt.close();
                        }
                        break label83;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Throwable var10) {
                    if (connect != null) {
                        try {
                            connect.close();
                        } catch (Throwable var7) {
                            var10.addSuppressed(var7);
                        }
                    }

                    throw var10;
                }

                if (connect != null) {
                    connect.close();
                }

                return var6;
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            System.out.println("Erro ao verificar código: " + e.getMessage());
        }

        return false;
    }

    public void readAllPecas() {
        String sql = "SELECT * FROM pecas";

        try {
            Connection connect = this.conexaoBD();

            try {
                PreparedStatement stmt = connect.prepareStatement(sql);

                try {
                    ResultSet rst = stmt.executeQuery();

                    while(rst.next()) {
                        int codigo = rst.getInt("codigo");
                        String nome = rst.getString(2);
                        double preco = rst.getDouble(3);
                        String tipo = rst.getString(4);
                        System.out.printf("Código: %d | Nome: %s | Preço: %.2f | Tipo: %s \n", codigo, nome, preco, tipo);
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var11) {
                            var12.addSuppressed(var11);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (Throwable var10) {
                        var13.addSuppressed(var10);
                    }
                }

                throw var13;
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException var14) {
            System.out.println("Falha ao buscar resultados");
        }

    }

    public void readPecaEspecifica(int codigo) {
        String sql = "SELECT * FROM pecas WHERE codigo = ?";

        try {
            Connection connect = this.conexaoBD();

            try {
                PreparedStatement stmt = connect.prepareStatement(sql);

                try {
                    stmt.setInt(1, codigo);
                    ResultSet rst = stmt.executeQuery();
                    if (rst.next()) {
                        codigo = rst.getInt("codigo");
                        String nome = rst.getString(2);
                        double preco = rst.getDouble(3);
                        String tipo = rst.getString(4);
                        System.out.printf("Código: %d | Nome: %s | Preço: %.2f | Tipo: %s \n", codigo, nome, preco, tipo);
                    } else {
                        System.out.println("Código de peça não encontrado");
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var11) {
                            var12.addSuppressed(var11);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (Throwable var10) {
                        var13.addSuppressed(var10);
                    }
                }

                throw var13;
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException var14) {
            System.out.println("Falha ao buscar resultados");
        }

    }


    public void updatePeca(Peca peca) throws Exception {
        String sql = "UPDATE pecas SET nome = ?, preco = ? WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, peca.getNome());
            stmt.setDouble(2, peca.getPreco());
            stmt.setInt(3, peca.getCodigo());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new Exception("Peça não encontrada.");
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar peça: " + e.getMessage());
        }
    }

    public void deletePeca(int codigo) throws Exception {
        String sql = "DELETE FROM pecas WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                throw new Exception("Peça não encontrada.");
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao excluir peça: " + e.getMessage());
        }
    }
}
