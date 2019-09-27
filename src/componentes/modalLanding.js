import React from 'react';
import Modal from "../../node_modules/react-bootstrap/Modal";
import "../css/style.css";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import barberShop from "../icones/barber-shop.png";
import hairCut from "../icones/hair-cut.png";

function ModalLanding(props) {
    return (
      <Modal
        {...props}
        size="md"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Entrar como
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div className="card_modal_escolha">
                <div className="imagem_modal_escolha">
                    <img src={barberShop} className="img" alt="Estabelecimento" />
                </div>
                <h4>Estabelecimento</h4>
            </div>
            <div className="card_modal_escolha">
                <div className="imagem_modal_escolha">
                    <img src={hairCut} className="img" alt="Cliente" />
                </div>
                <h4>Cliente</h4>
            </div>
        </Modal.Body>
      </Modal>
    );
}

export default ModalLanding;
