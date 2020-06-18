import React from 'react';
import Button from "../../node_modules/react-bootstrap/Button";
import Modal from "../../node_modules/react-bootstrap/Modal";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";
//import { Link } from 'react-router-dom';

function ModalSucess(props) {
    return (
      <Modal
        {...props}
        size="md"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title>
            Teste
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div className="container">
                <div className="col-4 bg-color-sucess">
                    teste
                </div>
                <div className="col-6">
                    teste
                </div>
            </div>
        </Modal.Body>
        <Modal.Footer>
            <Button variant="success">Save changes</Button>
        </Modal.Footer>
      </Modal>
    );
}

export default ModalSucess;
