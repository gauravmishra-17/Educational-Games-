import React, {Component} from 'react';
import GameService from "../services/GameService";
import CreateGame from "./addGame.css";

class AddGame extends Component {
    constructor(props) {
        super(props);

        this.state = {
            // step 2
            id: this.props.match.params.id,
            title: '',
            description: '',
            pictureUrl: '',
            genre: '',
            gameUrl: ''
        }
        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.saveOrUpdateGame = this.saveOrUpdateGame.bind(this);
        this.changeGenreHandler = this.changeGenreHandler.bind(this);
        this.changeGameUrlHandler = this.changeGameUrlHandler.bind(this);
        this.cancel = this.cancel.bind(this);
    }

    // step 3
    componentDidMount() {

        // step 4
        if(this.state.id === "_add"){
            return
        }
        else{
            GameService.getGameById(this.state.id).then((res) =>{
                let game = res.data;
                this.setState({
                    title: game.title,
                    description: game.description,
                    pictureUrl: game.pictureUrl,
                    genre: game.genre,
                    gameUrl: game.gameUrl
                });
            });
        }
    }

    saveOrUpdateGame = (e) => {
        e.preventDefault();

        let game = {title: this.state.title, description: this.state.description, pictureUrl: this.state.pictureUrl,
            genre: this.state.genre, gameUrl: this.state.gameUrl};
        console.log('game => ' + JSON.stringify(game));

        // step 5
        if(this.state.id === "_add"){
            GameService.createGame(game).then(res =>{
                this.props.history.push('/list-games');
            });
        }
        else{
            GameService.updateGame(game, this.state.id).then((res) => {
                this.props.history.push('/list-games');
            })
        }
    }
    changeTitleHandler = (e) => {
        this.setState({title: e.target.value});
    }

    changeDescriptionHandler = (e) => {
        this.setState({description: e.target.value});
    }

    changePictureUrlHandler = (e) => {
        this.setState({pictureUrl: e.target.value});
    }

    changeGenreHandler = (e) => {
        this.setState({genre: e.target.value});
    }

    changeGameUrlHandler = (e) => {
        this.setState({gameUrl: e.target.value});
    }

    cancel (){
        this.props.history.push('/list-games');
    }

    // step 6
    getTitle(){
        if(this.state.id === "_add"){
            return <h3 className={"text-center"}> Add Game </h3>
        }else{
            return  <h3 className={"text-center"}> Update Game </h3>
        }
    }
    render() {
        return (
            <div>
                <div className={"container addForm"}>
                    <div className={"row"}>
                        <div className={"card col-md-6 offset-md-3 offset-md-3 "}>
                            { this.getTitle() }
                            <div className={"card-body"}>
                                <form>
                                    <div className={"form-group"}>
                                        <label> Game Title: </label>
                                        <input className={"form-control"}
                                               placeholder={"Game Title"} name={"title"}
                                               value={this.state.title} onChange={this.changeTitleHandler}/>
                                    </div>
                                    <div className={"form-group"}>
                                        <label> Description: </label>
                                        <input className={"form-control"}
                                               placeholder={"Description"} name={"description"}
                                               value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                    </div>
                                    <div className={"form-group"}>
                                        <label> Picture Url: </label>
                                        <input className={"form-control"}
                                               placeholder={"pictureUrl"} name={"pictureUrl"}
                                               value={this.state.pictureUrl} onChange={this.changePictureUrlHandler}/>
                                    </div>
                                    <div className={"form-group"}>
                                        <label> Genre: </label>
                                        <input className={"form-control"}
                                               placeholder={"Genre"} name={"genre"}
                                               value={this.state.genre} onChange={this.changeGenreHandler}/>
                                    </div>
                                    <div className={"form-group"}>
                                        <label> Game Url: </label>
                                        <input className={"form-control"}
                                               placeholder={"Game Url"} name={"gameUrl"}
                                               value={this.state.gameUrl} onChange={this.changeGameUrlHandler}/>
                                    </div>

                                    <button className={"btn btn-success"}
                                            onClick={this.saveOrUpdateGame}>Save</button>
                                    <button className={"btn btn-primary"}
                                            onClick={this.cancel} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddGame;
