import React, {Component} from 'react';
import GameService from "../services/GameService";
import './listProducts.css';

class ListProducts extends Component {
    constructor(props) {
        super(props)
        this.state= {
            games:[]
        }
        this.addGame = this.addGame.bind(this);
        this.editGame = this.editGame.bind(this);
        this.deleteGame = this.deleteGame.bind(this);
        this.viewGameDetails = this.viewGameDetails.bind(this);
    }
    componentDidMount() {
        GameService.getGames().then((res) =>{
            this.setState({ games: res.data});
        });
    }
    // configure the route component to add-game endpoint
    addGame(){
        this.props.history.push('/add-game/_add');
    }

    editGame(id){
        this.props.history.push(`/add-game/${id}`)
    }

    deleteGame(id){
        GameService.deleteGame(id).then((res) => {
            this.setState({games: this.state.games.filter(game => game.id !== id)});
        });
    }

    viewGameDetails(id){
        this.props.history.push(`/view-game/${id}`);
    }

    render() {
        return (
            <div>
                <h2 className={"text-center title"}>Games List</h2>
                <div className={"row"}>
                    <button className={"btn btn-primary addGame"} 
                    onClick={this.addGame}> Add Game </button>
                </div>
                <div className={"container displayGamesList"}>
                    <div className={"table-responsive-sm table-responsive-md w-auto"}>
                        <table className={"table"}>
                            <thead>
                            <tr>
                                <th> Game Title </th>
                                <th> Genre </th>
                                <th> Game Url </th>
                                <th> Actions </th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.games.map(
                                    game =>
                                        <tr key = {game.id}>
                                            <td className={"td"}> {game.title} </td>
                                            <td className={"td"}> {game.genre} </td>
                                            <td className={"td"}>{game.gameUrl}</td>
                                            <td>
                                                <button onClick={() => this.editGame(game.id)}
                                                        className={"btn btn-info bt"}>Update
                                                </button>
                                                <button onClick={() => this.deleteGame(game.id)}
                                                        className={"btn btn-danger bt"}>Delete
                                                </button>
                                                <button onClick={() => this.viewGameDetails(game.id)}
                                                        className={"btn btn-secondary bt"} >Details
                                                </button>
                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default ListProducts;
