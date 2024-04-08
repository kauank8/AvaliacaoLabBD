Use AvLabBd
go
--Populandos tabelas necessarias;
Insert into Curso Values
(1,'Analise e Desenvolvimento de Sistemas', 2800, 'ADS', 4),
(2, 'Engenharia de Computação', 3600, 'ECOM', 4),
(3, 'Administração', 3200, 'ADM', 3)
GO
Insert Into Disciplina
Values
('Sistemas Operacionais', 4, '13:00:00', '16:30:00', 'Segunda-feira', 1),
('Redes de Computadores', 2, '13:00:00', '14:40:00', 'Terça-feira', 1),
('Programação II', 4, '14:50:00', '18:20:00', 'Quarta-feira', 1),
('Banco de Dados Avançado', 2, '14:50:00', '16:30:00', 'Quinta-feira', 1),
('Desenvolvimento Mobile', 2, '16:40:00', '18:20:00', 'Sexta-feira', 1),
('Inteligência Artificial', 4, '13:00:00', '16:30:00', 'Segunda-feira', 1),
('Segurança da Informação', 2, '13:00:00', '14:40:00', 'Terça-feira', 1),
('Gestão de Projetos', 4, '14:50:00', '18:20:00', 'Quarta-feira', 1),
('Análise e Projeto de Sistemas', 2, '14:50:00', '16:30:00', 'Quinta-feira', 1),
('Empreendedorismo', 2, '16:40:00', '18:20:00', 'Sexta-feira', 1),
('Programação I', 4, '13:00:00', '16:30:00', 'Segunda-feira', 1),
('Banco de Dados', 2, '13:00:00', '14:40:00', 'Terça-feira', 1),
('Engenharia de Software', 4, '14:50:00', '18:20:00', 'Quarta-feira', 1),
('Estrutura de Dados', 2, '14:50:00', '16:30:00', 'Quinta-feira', 1),
('Desenvolvimento Web', 2, '16:40:00', '18:20:00', 'Sexta-feira', 1),
('Sistemas Operacionais III', 2, '16:40:00', '18:20:00', 'Segunda-feira', 1)

Go 
-- Materias de Adm
INSERT INTO Disciplina 
VALUES 
('Gestão de Recursos Humanos', 4, '13:00:00', '16:30:00', 'Segunda-feira', 1),
('Contabilidade Gerencial', 2, '13:00:00', '14:40:00', 'Segunda-feira', 1),
('Marketing Estratégico', 4, '14:50:00', '18:20:00', 'Segunda-feira', 1),
('Gestão de Projetos', 2, '14:50:00', '16:30:00', 'Segunda-feira', 1),
('Economia Empresarial', 2, '16:40:00', '18:20:00', 'Segunda-feira', 1),
('Logística Empresarial', 4, '13:00:00', '16:30:00', 'Terça-feira', 1),
('Finanças Corporativas', 2, '13:00:00', '14:40:00', 'Terça-feira', 1),
('Comportamento Organizacional', 4, '14:50:00', '18:20:00', 'Terça-feira', 1),
('Administração de Vendas', 2, '14:50:00', '16:30:00', 'Terça-feira', 1),
('Empreendedorismo', 2, '16:40:00', '18:20:00', 'Terça-feira', 1),
('Ética nos Negócios', 4, '13:00:00', '16:30:00', 'Quarta-feira', 1),
('Gestão da Qualidade', 2, '13:00:00', '14:40:00', 'Quarta-feira', 1),
('Análise de Investimentos', 4, '14:50:00', '18:20:00', 'Quarta-feira', 1),
('Comunicação Empresarial', 2, '14:50:00', '16:30:00', 'Quarta-feira', 1),
('Liderança e Motivação', 2, '16:40:00', '18:20:00', 'Quarta-feira', 1),
('Estratégia Empresarial', 4, '13:00:00', '16:30:00', 'Quinta-feira', 1),
('Negociação Empresarial', 2, '13:00:00', '14:40:00', 'Quinta-feira', 1),
('Gestão de Mudanças', 4, '14:50:00', '18:20:00', 'Quinta-feira', 1),
('Direito Empresarial', 2, '14:50:00', '16:30:00', 'Quinta-feira', 1),
('Inovação e Tecnologia', 2, '16:40:00', '18:20:00', 'Quinta-feira', 1),
('Gestão Ambiental', 4, '13:00:00', '16:30:00', 'Sexta-feira', 1),
('Gestão de Custos', 2, '13:00:00', '14:40:00', 'Sexta-feira', 1),
('Empresas Familiares', 4, '14:50:00', '18:20:00', 'Sexta-feira', 1),
('Gestão de Conflitos', 2, '14:50:00', '16:30:00', 'Sexta-feira', 1),
('Gestão de Riscos', 2, '16:40:00', '18:20:00', 'Sexta-feira', 1);
