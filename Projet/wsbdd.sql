-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 23 Mai 2018 à 12:21
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;






INSERT INTO `conseiller` (`idConseiller`, `login`, `nomConseiller`, `password`, `prenomConseiller`) VALUES
(1, 'dubcons', 'DUBOIS', '123', 'Constance'),
(2, 'mouja', 'MOULIN', '456', 'Jaque');






INSERT INTO `clientproxi` (`idClient`, `adresseClient`, `emailClient`, `nomClient`, `prenomClient`, `conseiller_idConseiller`) VALUES
(1, '70 rue Paris,69800 Lyon', 'j.dupont@hotmail.fr', 'Jeanne', 'Dupont',1),
(2, '20 avenue Salengro,75005 Paris', 'wilson.marc@gmail.fr', 'WILSON', 'Marc', 2),
(3, '45 rue Parilly,85007 Toulouse', 'doumi.elsa@gmail.fr', 'DOUMI', 'Elsa', 1),
(4, '35 rue Pompidou,13007 Marseille', 'minala.moussa@hotmail.fr', 'MINALA', 'Moussa', 2),
(5, '60 rue Liberte,69800 Lyon', 'jacot.emilie@hotmail.fr', 'JACOT', 'Emilie', 1),
(6, '159 rue de la Loge,15020 Trémouille', 'martin.thomas@hotmail.fr', 'Martin', 'Thomas', 2);





INSERT INTO `compte` (`idCompte`, `decouvertMaxCompte`, `numCompte`, `soldeCompte`, `typeCompte`, `client_idClient`) VALUES
(1, -1000, 'C1234', 20000, 'Courant', 1),
(2, 0, 'E1234', 25000, 'Epargne', 1),
(3, -1000, 'C2456', 1000, 'Courant', 2),
(4, 0, 'E2456', 31000, 'Epargne', 2),
(5, -1000, 'C3456', 20000, 'Courant', 3),
(6, 0, 'E3456', 40000, 'Epargne', 3),
(7, -1000, 'C4567', 1000, 'Courant', 4),
(8, 0, 'E4567', 50000, 'Epargne', 4),
(9, -1000, 'C5678', 1000, 'Courant', 5),
(10, 0, 'E5678', 60000, 'Epargne', 5);


INSERT INTO `transaction` (`idTransaction`, `date`, `idCompteBeneficiaire`, `idCompteEmetteur`, `montant`) VALUES
(2, '2018-04-24 14:27:39.000000', 1, 2, 20),
(3, '2018-05-16 18:15:37.000000', 4, 3, 20),
(4, '2018-05-16 18:16:41.000000', 4, 3, 20),
(5, '2018-05-16 18:18:53.000000', 4, 3, 20),
(6, '2018-05-20 18:29:50.000000', 4, 3, 200),
(7, '2018-05-20 18:29:52.000000', 4, 3, 200),
(8, '2018-05-20 18:42:03.000000', 4, 3, 300),
(9, '2018-05-20 18:56:18.000000', 3, 4, 10),
(10, '2018-05-20 20:34:54.000000', 3, 4, 10),
(11, '2018-05-20 20:35:43.000000', 3, 4, 10),
(12, '2018-05-22 18:14:13.000000', 31, 32, 1000),
(13, '2018-05-22 18:17:39.000000', 37, 38, 1000),
(14, '2018-05-22 18:21:46.000000', 43, 44, 1000),
(15, '2018-05-22 18:22:49.000000', 49, 50, 1000),
(16, '2018-05-22 18:46:17.000000', 55, 56, 1000),
(17, '2018-05-22 19:03:33.000000', 61, 62, 1000),
(18, '2018-05-22 19:11:42.000000', 67, 68, 1000),
(19, '2018-05-22 19:13:30.000000', 73, 74, 1000),
(20, '2018-05-22 20:17:42.000000', 13, 14, 1000),
(21, '2018-05-22 20:38:43.000000', 19, 20, 1000),
(22, '2018-05-22 21:06:53.000000', 3, 4, 10),
(23, '2018-05-22 22:07:36.000000', 25, 26, 1000),
(43, '2018-05-23 11:42:09.000000', 9, 2, 2),
(44, '2018-05-23 11:43:10.000000', 2, 1, 50),
(45, '2018-05-23 11:44:28.000000', 2, 1, 4000),
(46, '2018-05-23 11:46:20.000000', 1, 3, 18000),
(47, '2018-05-23 12:00:32.000000', 3, 1, 150),
(48, '2018-05-23 12:00:32.000000', 1, 3, 150),
(49, '2018-05-23 12:00:32.000000', 3, 1, 150),
(50, '2018-05-23 12:00:33.000000', 1, 3, 150),
(51, '2018-05-23 12:02:10.000000', 4, 3, 200),
(52, '2018-05-23 12:31:39.000000', 3, 1, 150),
(53, '2018-05-23 12:31:39.000000', 1, 3, 150),
(54, '2018-05-23 12:31:39.000000', 3, 1, 150),
(55, '2018-05-23 12:31:39.000000', 1, 3, 150),
(56, '2018-05-23 12:31:40.000000', 3, 1, 150),
(57, '2018-05-23 12:31:40.000000', 1, 3, 150),
(58, '2018-05-23 12:31:40.000000', 3, 1, 150),
(59, '2018-05-23 12:31:40.000000', 1, 3, 150),
(60, '2018-05-23 13:43:39.000000', 17, 18, 1000),
(61, '2018-05-23 13:43:40.000000', 23, 24, 1000),
(62, '2018-05-23 14:11:04.000000', 4, 3, 200),
(63, '2018-05-23 14:14:23.000000', 3, 1, 150),
(64, '2018-05-23 14:14:23.000000', 1, 3, 150),
(65, '2018-05-23 14:14:23.000000', 3, 1, 150),
(66, '2018-05-23 14:14:23.000000', 1, 3, 150),
(67, '2018-05-23 14:16:03.000000', 4, 3, 200);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `clientproxi`
--
ALTER TABLE `clientproxi`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `FKpl4ymaknup9k2jb6lh3fel5pq` (`conseiller_idConseiller`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`idCompte`),
  ADD KEY `FKr65glp5djqob27bexp32pndep` (`client_idClient`);

--
-- Index pour la table `conseiller`
--
ALTER TABLE `conseiller`
  ADD PRIMARY KEY (`idConseiller`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idTransaction`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `clientproxi`
--
ALTER TABLE `clientproxi`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT pour la table `conseiller`
--
ALTER TABLE `conseiller`
  MODIFY `idConseiller` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idTransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `clientproxi`
--
ALTER TABLE `clientproxi`
  ADD CONSTRAINT `FKpl4ymaknup9k2jb6lh3fel5pq` FOREIGN KEY (`conseiller_idConseiller`) REFERENCES `conseiller` (`idConseiller`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FKr65glp5djqob27bexp32pndep` FOREIGN KEY (`client_idClient`) REFERENCES `clientproxi` (`idClient`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
