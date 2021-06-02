import React, {Component} from 'react';
import "./displayGameThumbanil.css";
import GameService from "../services/GameService";


class DisplayGameThumbnail extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            game: {},
        }
    }
    componentDidMount() {
        GameService.getGames(this.state.id).then(res =>{
            this.setState({game: res.data});
        })
    }
    render() {

        const returnedArray = Array.from(this.state.game);
        return ( returnedArray.map(game =>
                <div className="col-sm-12 col-sm-6 col-md-3" key={game.id}>
                    <div className="thumbnail games">
                        {/*<div className={"row"}>*/}
                            <a href={game.gameUrl}>
                                <div className={"picTitle"}>{game.title}</div>
                                        <img className={"imageStyle"} src={game.pictureUrl}
                                             alt={game.title} title={game.description}/>
                            </a>
                        </div>
                    {/*</div>*/}
                </div>
            )
        )
    }
}

export default DisplayGameThumbnail;

