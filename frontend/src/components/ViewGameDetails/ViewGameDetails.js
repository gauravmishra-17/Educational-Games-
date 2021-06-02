import React, {Component} from 'react';
import GameService from "../services/GameService";
import './viewGameDetails.css'

class ViewGameDetails extends Component {
    constructor(props) {
        super(props);

        this.state = {
            id: this.props.match.params.id,
            game: {}
        }
        this.cancel = this.cancel.bind(this);
        this.editGame = this.editGame.bind(this);
    }

    componentDidMount() {
        GameService.getGameById(this.state.id).then(res =>{
            this.setState({ game:  res.data});
        })
    }

    cancel (){
        this.props.history.push('/list-games');
    }

    editGame(id){
        this.props.history.push(`/add-game/${id}`)
    }

    render() {
        return (
            <div>
                <br/>
                <div className={"card col-md-6 offset-md-3 card"}>
                    <h3 className={"text-center"}> Game Details </h3>
                    <div className={"card-body"}>
                        <div className={"row"}>
                            <lable> Title: </lable>
                            <div className={"info"}> { this.state.game.title }</div>
                        </div>
                        <div className={"row"}>
                            <lable> Description: </lable>
                            <div className={"info"}> { this.state.game.description }</div>
                        </div>
                        <div className={"row"}>
                            <lable> picture Url: </lable>
                            <div className={"info"}> { this.state.game.pictureUrl }</div>
                        </div>
                        <div className={"row"}>
                            <lable> Genre: </lable>
                            <div className={"info"}> { this.state.game.genre }</div>
                        </div>
                        <div className={"row"}>
                            <lable> Game Url: </lable>
                            <div className={"info"}> { this.state.game.gameUrl }</div>
                        </div>
                    </div>
                </div>
                <div className="col text-center button">
                 <button onClick={() => this.editGame(this.state.game.id)}
                         className={"btn btn-primary bt"}>Edit
                 </button>
                <button className={"btn btn-primary bt"}
                        onClick={this.cancel} style={{marginLeft: "10px"}}>Back to List
                </button>
                </div>
            </div>
        );
    }
}

export default ViewGameDetails;
