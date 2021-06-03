import React, {Component} from 'react'
import axios from 'axios'
import "./contact.css"


class Contact extends Component {
    constructor() {
        super()
        this.handleSubmit = this.handleSubmit.bind(this)
        this.nameChanged = this.nameChanged.bind(this)
        this.emailChanged = this.emailChanged.bind(this)
        this.messageChanged = this.messageChanged.bind(this)
        this.state = {
            email: '',
            name: '',
            message: '',
            sent: false
        }
    }
    nameChanged(e) {
        this.setState({name: e.target.value})
    }
    messageChanged(e) {
        this.setState({message: e.target.value})
    }
    emailChanged(e) {
        this.setState({email: e.target.value})
    }
    async handleSubmit(e) {
        e.preventDefault()
        try {
            const res = await axios('/contact', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({email: this.state.email, name: this.state.name, message: this.state.message})
            })
            res.status === 200
                ? this.setState({sent: true})
                : this.setState({sent: false})
        } catch (err) {
            console.log(err)
        }
    }
    render() {
        return (<div>
            <div className={"header"}>
                <h1 className={"title"}>Sent us a message! </h1>
            </div>

            <p className={this.state.sent
                ? ''
                : 'hide-text'}><div className={"thanks"}>Thank you for your message.</div></p>
            <form onSubmit={this.handleSubmit} id='form'>
                <div>
                    <p>
                        <label className={"field"} htmlFor="name">Name:</label><br/>
                        <input name='name' type='text' value={this.state.name} onChange={this.nameChanged}/></p>
                    <p>
                        <label className={"field"} htmlFor="email">Email:</label><br/>
                        <input name='email' type='text' value={this.state.email} onChange={this.emailChanged}/></p>
                    <p>
                        <label className={"field"} htmlFor="message">Message:</label><br/>
                        <textarea name='message' cols={'140'} rows={'10'} type="text" value={this.state.message} onChange={this.messageChanged}/></p>
                    <p><input className={"btn btn-danger submit"} type='submit' value='Send'/></p>
                </div>
            </form>
        </div>)
    }
}

export default Contact
