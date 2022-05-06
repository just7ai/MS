theme: /
    state: Start
        q!: $regex</start>
        a: Start
        
    state: SubscribeOffer
        q!: push
        a: Хотите подписаться на нашу рассылку?
    
        state: Subscribe
            q!: yes
            script:
                # Создание пушбэка.
                $session.pushback = $pushgate.createPushback(
                    $request.channelType,
                    $request.botId,
                    $request.channelUserId,
                    "newNotification",
                    {}
                );

                
            a: Ура, вы подписаны! Теперь вы будете первым узнавать о наших акциях и предложениях.
            a: {{ toPrettyString($session.pushback) }}

    state: NewNotification || noContext = true
        event!: newNotification
        a: {{ toPrettyString($request) }}